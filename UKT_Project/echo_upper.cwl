cwlVersion: v1.2
class: Workflow 

inputs: 
  message: 
    type: string

outputs:
  out: 
    outputSource: uppercase/uppercase_message
    type: string

steps: 
  uppercase:
    run : uppercase.cwl
    in: 
      message: 
        source: echo/out
    out: [uppercase_message]
  echo:
    run : echo.cwl
    in: 
      message: 
        source: message
    out: [out]
