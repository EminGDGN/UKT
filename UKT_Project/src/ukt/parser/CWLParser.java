package ukt.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ukt.model.cwlModel.CommandLineTool;
import ukt.model.cwlModel.Input;
import ukt.model.cwlModel.Output;
import ukt.model.cwlModel.Process;
import ukt.model.cwlModel.Step;
import ukt.model.cwlModel.Types;
import ukt.model.cwlModel.Workflow;

public class CWLParser {
	
	private String path;

	public CWLParser(String path) {
		this.path = path;
	}
	
	public Step parse(String name) {
		Step result = null;
		try{
			FileInputStream fis = new FileInputStream(new File(path+"/"+name+".cwl"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			//In cwl file version has form cwlVersion : v1.2
			float version = Float.valueOf(br.readLine().split(": v")[1]).floatValue();
			String classCWL = br.readLine();
			if(classCWL.equals("class: Workflow")) {
				result = new Step(new Workflow(version, name, null));
				
			}else{
				skip(br);
				String baseCommand = "";
				try {
					baseCommand = br.readLine().split(": ")[1];
				}catch(Exception e) {
					
				}
				result = new Step(new CommandLineTool(version, name, baseCommand,null));
				
				while(!br.readLine().equals("inputs:"));
				Input temp;
				while((temp = parseInputs(br, result.process())) != null) {
					result.process().addInput(temp);
				}
				
				Output temp2;
				while((temp2 = parseOutputs(br, result.process())) != null) {
					result.process().addOutput(temp2);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return result;
	}
	
	public Input parseInputs(BufferedReader br, Process parent) throws IOException{
		try {
			String[] temp = br.readLine().split(":");
			String name = temp[0].trim();
			if(!name.equals("outputs")) {
				String type = temp[1].trim();
				type = (!type.equals(""))?type : br.readLine().split(": ")[1];
				return new Input(name, Types.valueOf(type.toUpperCase()), parent);
			}
			return null;
		}catch(IllegalArgumentException e) {
			skip(br);
			return null;
		}
	}
	
	public Output parseOutputs(BufferedReader br, Process parent) throws IOException{
		try {
			String[] temp = br.readLine().split(":");
			String name = temp[0].trim();
			if(!name.equals("")) {
				String type = (temp.length > 1)?temp[1].trim() : br.readLine().split(": ")[1];
				return new Output(name, Types.valueOf(type.toUpperCase()), parent);
			}
			return null;
		}catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	public void skip(BufferedReader br) throws IOException {
		br.readLine();
	}
	
	public Workflow merge(ArrayList<String> files) {
		ArrayList<Step> steps = new ArrayList<>();
		
		for(String s : files) {
			steps.add(parse(s));
		}
		
		String nameMerge = "";
		ArrayList<Output> previousOutput = null;
		ArrayList<Output> firstOutputs = null;
		Workflow result = new Workflow(1.2f, nameMerge, null);
		int i = 1;
		for(Step s : steps) {
			Process p = s.process();
			nameMerge += p.getName();
			if(previousOutput != null) {
				
			}else {
				result.setInputs(p.getInputs());
				firstOutputs = p.getOutputs();
			}
			
			if(i == steps.size()) {
				for(Output o : firstOutputs) {
					result.addOutput(new Output(o.getName(),o.getType(), result));
				}
			}
			else {
				i++;
			}
		}
		
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(nameMerge));
			
			
			return result;
		}catch(IOException e) {
			return null;
		}
	}
}
