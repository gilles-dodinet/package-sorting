# PACKAGE SORTING 


## Objective 

A function that allows a robotic arm to dispatch packages to the correct stack according to their volume and mass.

## Rules

Sort the packages using the following criteria:

- A package is **bulky** if its volume (Width x Height x Length) is greater than or equal to 1,000,000 cm³ or when one of its dimensions is greater or equal to 150 cm.
- A package is **heavy** when its mass is greater or equal to 20 kg.

You must dispatch the packages in the following stacks:

- **STANDARD**: standard packages (those that are not bulky or heavy) can be handled normally.
- **SPECIAL**: packages that are either heavy or bulky can't be handled automatically.
- **REJECTED**: packages that are both heavy and bulky are rejected.

## Implementations

### Two implementations
- A naive implementation: 
  - ..sorting.native.Sorter 
  - about 10 minutes, including tests
- A refined implementation: 
  - ..sorting.service.Dispatcher 
  - uses jakarta.validation to sort packages  
  - about 25 minutes, including tests 
  - about 10 minutes to bootstrap the project)

### Other notes
- 
- Using java.util.logging to streamline logging setup (vs slf4j, f.i.) 