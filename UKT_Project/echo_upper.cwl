cwlVersion: v1.2
class: Workflow 

inputs: 
  String: 
    type: string

outputs: 

steps: 
  ToUpperCase:
    run : ToUpperCase.cwl
    in: 
      String: 
        type: string
        source: String
    out: 
      StringUpper: 
        type: int
  Echo:
    run : Echo.cwl
    in: 
      StringUpper: 
        source: ToUpperCase/StringUpper
    out: 
