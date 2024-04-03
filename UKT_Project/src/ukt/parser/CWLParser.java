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
import ukt.model.cwlModel.InputParameters.InputType;
import ukt.model.cwlModel.InputParameters.Source;
import ukt.model.cwlModel.OutputParameters.OutputSource;
import ukt.model.cwlModel.OutputParameters.OutputType;

public class CWLParser {

	public CWLParser() {
	}
	
	public boolean isWorkflow(File f) {
		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			//In cwl file version has form cwlVersion : v1.2
			br.readLine();
			String classCWL = br.readLine();
			
			return classCWL.equals("class: Workflow");
		} catch(IOException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public Step parse(File file, Process parent) {
		Step result = null;
		try{
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			//In cwl file version has form cwlVersion : v1.2
			float version = Float.valueOf(br.readLine().split(": v")[1]).floatValue();
			String classCWL = br.readLine();
			if(classCWL.equals("class: Workflow")) {
				result = new Step(new Workflow(version, this.getNameWithoutExtansion(file.getName()), parent));
				
			}else{
				skip(br);
				String baseCommand = "";
				try {
					baseCommand = br.readLine().split(": ")[1];
				}catch(Exception e) {
					
				}
				result = new Step(new CommandLineTool(version, this.getNameWithoutExtansion(file.getName()), baseCommand, parent));
				
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
				String type = (temp.length > 1)?temp[1].trim():""; 
				type = (type.equals(""))? br.readLine().split(": ")[1] : type;
				Input in = new Input(name, parent);
				in.addInputParameter(new InputType(in, Types.valueOf(type.toUpperCase())));
				return in;
			}
			return null;
		}
		catch(IllegalArgumentException e) {
			while(!br.readLine().trim().equals("outputs:"));
			return null;
		}
	}
	
	public Output parseOutputs(BufferedReader br, Process parent) throws IOException{
		try {
			String[] temp = br.readLine().split(":");
			String name = temp[0].trim();
			if(!name.equals("")) {
				String type = (temp.length > 1)?temp[1].trim() : br.readLine().split(": ")[1];
				Output out = new Output(name, parent);
				out.addOutputParameter(new OutputType(out, Types.valueOf(type.toUpperCase())));
				return out;
			}
			return null;
		}catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	public void skip(BufferedReader br) throws IOException {
		br.readLine();
	}
	
	public Workflow merge(File f1, File f2) {
		ArrayList<Step> steps = new ArrayList<>();
		Workflow result = new Workflow(1.2f, "", null);
		steps.add(parse(f1, result));
		steps.add(parse(f2, result));
		
		String nameMerge = "";
		Output firstOut = null;
		for(Step s : steps) {
			Process p = s.process();
			nameMerge += p.getName();
			if(firstOut == null) {
				
				Input in = p.getInputs().get(0);
				Input clone = in.clone(result);
				result.addInput(clone);
				in.addInputParameter(new Source(in, clone));
				firstOut = p.getOutputs().get(0);
				
				nameMerge += "_";
				
			}else {
				Input in = p.getInputs().get(0);
				in.addInputParameter(new Source(in, firstOut));
				Output o = p.getOutputs().get(0);
				Output out = new Output(o.getName(), result);
				out.addOutputParameter(new OutputType(out, o.getType()));
				out.addOutputParameter(new OutputSource(out, o));
				result.addOutput(out);
			}
			
		}
		result.setName(nameMerge);
		result.setStep(steps);
		return result;
	}
	
	public String getNameWithoutExtansion(String f) {
		return f.replaceFirst("[.][^.]+$", "");
	}
}
