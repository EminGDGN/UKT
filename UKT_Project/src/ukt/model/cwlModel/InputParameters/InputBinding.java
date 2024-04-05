package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;

public class InputBinding extends InputParameter{
	
		//TO DO -> not complete (only takes position in account

		private int position;
		
		public InputBinding(Input parent, int position) {
			super(parent);
			this.position = position;
//			this.prefix = null;
//			this.separate = false;
		}
		
		/**
		 * @return a string describing an input binding for an output in cwl format
		 */
		@Override
		public String toString() {
			return this.tab() + "InputBinding: " +
				   this.tab() + "  " + "position: " + this.position;
		}
}
