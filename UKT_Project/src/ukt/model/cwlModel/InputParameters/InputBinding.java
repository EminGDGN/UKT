package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;

public class InputBinding extends InputParameter{

		private int position;
//		private String prefix;
//		private boolean separate;
	
//		public InputBinding(int position, String prefix, boolean separate) {
//			this.position = position;
//			this.prefix = prefix;
//			this.separate = separate;
//		}
		
		public InputBinding(Input parent, int position) {
			super(parent);
			this.position = position;
//			this.prefix = null;
//			this.separate = false;
		}
		
		
		@Override
		public String toString() {
			return this.tab() + "InputBinding: " +
				   this.tab() + "  " + "position: " + this.position;
		}
}
