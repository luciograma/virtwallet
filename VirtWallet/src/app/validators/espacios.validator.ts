import {AbstractControl} from "@angular/forms";

export function validarEspacios(abstractControl: AbstractControl){
  if(abstractControl.value == null) {
    return null;
  }
  if(abstractControl.value.includes(' ')){
    return {contieneEspacios: true};
  }
  return null;
}
