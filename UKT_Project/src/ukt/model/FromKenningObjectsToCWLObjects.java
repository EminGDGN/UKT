package ukt.model;

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
import ukt.model.kenningModel.Graph;
import ukt.model.kenningModel.Node;
import ukt.model.kenningModel.Parameter;

/**
 * This class is used to translate a graph (in Kenning objects) to a process (in cwl objects)
 */

public class FromKenningObjectsToCWLObjects {

	Workflow workflow;				//the translated workflow
	Graph graph;					//the graph to transalte

	public FromKenningObjectsToCWLObjects(Graph graph) {
		this.graph = graph;
		this.workflow = new Workflow(1.2f, graph.getName(), null); //define a new enncompassing workflow
		this.translate();
	}

	public Workflow getCWLWorkflow() {
		return this.workflow;
	}
	/**
	 * tra,nslate a graph to a workflow
	 */
	private void translate() {
		this.createAllSteps(graph.getAllNodes());

		this.setMainInParameters();

		ArrayList<Parameter> mainInParameters = this.getMainInParameters();
		this.linkParameters(this.workflow, mainInParameters);

		this.setMainOutParameters();

	}

	/**
	 * 
	 * @return All the graph inputs parameters in the first nodes
	 */
	private ArrayList<Parameter> getMainInParameters() {

		ArrayList<Parameter> parameters = new ArrayList<>();

		for (Node node : this.graph.getInitialNodes()) {
			parameters.addAll(node.getInParameters());
		}

		return parameters;
	}

	/**
	 * 
	 * @return All the graph outputs parameters in the last nodes
	 */
	private ArrayList<Parameter> getMainOutParameters() {

		ArrayList<Parameter> parameters = new ArrayList<>();

		for (Node node : this.graph.getFinalNodes()) {
			parameters.addAll(node.getOutParameters());
		}

		return parameters;
	}

	/*
	 * Define all the main workflow input based on graph parameters
	 */
	private void setMainInParameters() {
		for (Parameter parameter : this.getMainInParameters()) {
			Input input = new Input(parameter.getName(), this.workflow);
			input.addInputParameter(new InputType(input, Types.getEnum(parameter.getType().toLowerCase())));
			this.workflow.addInput(input);
		}
	}

	/*
	 * Define all the main workflow outputs based on graph parameters
	 */
	private void setMainOutParameters() {
		for (Parameter parameter : this.getMainOutParameters()) {
			Output output = new Output(parameter.getName(), this.workflow);
			output.addOutputParameter(new OutputType(output, Types.getEnum(parameter.getType().toLowerCase())));
			output.addOutputParameter(new OutputSource(output,
					this.workflow.getStepByName(this.graph.getNodeOfParameter(parameter).getName()).getProcess()
							.getLinkableByName(parameter.getName())));
			this.workflow.addOutput(output);
		}
	}

	/**
	 * This recursive method allows translate all the nodes to process
	 * 										  all the IN parameters to inputs
	 * 										  all the OUT parameters to outputs
	 * And links every step outputs to its corresponding step inputs
	 * @param previousProcess
	 * @param inParameters
	 */
	private void linkParameters(Process previousProcess, ArrayList<Parameter> inParameters) {
		//For all the parameters 
		for (Parameter inParameter : inParameters) {
			
			//get the next nodes connected to this specific parameter
			ArrayList<Node> targets = this.graph.getNodeConnectedTo(inParameter);
			if (targets != null) {
				//For all nodes this parameter is connected to
				for (Node node : targets) {
					
					Process process = this.workflow.getStepByName(node.getName()).getProcess();			//get the step correpsonding to the node connected to the parameter
					Input input = new Input(this.getCorrespondingParameterName(node, inParameter), process);
					// InputType(input,Types.getEnum(inParameter.getType().toLowerCase())));			// For steps it is best to not include the type
					input.addInputParameter(new Source(input, previousProcess.getLinkableByName(inParameter.getName()))); //adds the input to the last fetch process
					process.addInput(input);

					ArrayList<Parameter> outParameters = node.getOutParameters();	//all the parameters going out of this specific node
					for (Parameter outParameter : outParameters) {
						//adds all the inputs to the process
						Output output = new Output(outParameter.getName(), process);
						// output.addOutputParameter(new OutputType(output,Types.getEnum(outParameter.getType().toLowerCase()))); //better not to include type in the steps parameter
						process.addOutput(output);

						linkParameters(process, outParameters); //recursive call for this process and its OUT parameters
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param nodes, all the node contained in the graph
	 * @sideEffect the workflow conatins a arrayList of steps equivalent to the nodes
	 */
	private void createAllSteps(ArrayList<Node> nodes) {
		for (Node node : nodes) {
			this.workflow.addStep(new Step(new CommandLineTool(1.2f, node.getName(), node.getName(), this.workflow)));
		}
	}

	/**
	 * 
	 * @param n, the node where the p parameter is coming from
	 * @param p, the parameter we want the name in the previous node n 
	 * @return
	 */
	private String getCorrespondingParameterName(Node n, Parameter p) {
		ArrayList<String> ids = this.graph.getNextParametersID(p);
		ArrayList<Parameter> parameters = n.getInParameters();
		if (ids != null && parameters != null) {
			
			//Tries to find an a p.id equals to one of the id in the node n
			for (String string : ids) {
				for (Parameter parameter : parameters) {
					if (string.equals(parameter.getId())) {
						return parameter.getName();
					}
				}
			}
		}
		return p.getName(); //if come here, it means ids is null
							//it means p is a IN parameter
							//if p is a IN parameter then it's a main workflow input
							//so its name is the parameter.name 
	}

}
