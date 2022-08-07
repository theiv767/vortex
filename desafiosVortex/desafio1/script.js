const input = document.getElementById("text-box");
const result = document.getElementById("result");
const btn = document.getElementById("button");

const romanNums = new Array("i", "v", "x", "l", "c", "d", "m"); // numeros romanos em minusculas
const decimalNums = new Array(1, 5, 10, 50, 100, 500, 1000); // valores correspondentes a cada posição de romanNums

btn.onclick = translate;

function verification(value) {
  let aux = "";
  let repeat = 1;
  for (let i = 0; i < value.length; i++) {
    let valid = false;
    valueTest = value[i].toLowerCase(); //padronizando a entrada

    switch (valueTest) { //verificando se o algarismo existe
      case romanNums[0]:
        valid = true;
        break;
      case romanNums[1]:
        valid = true;
        break;
      case romanNums[2]:
        valid = true;
        break;
      case romanNums[3]:
        valid = true;
        break;
      case romanNums[4]:
        valid = true;
        break;
      case romanNums[5]:
        valid = true;
        break;
      case romanNums[6]:
        valid = true;
        break;
    }

    if (!valid) {
      return false;
    }

    if (valueTest == aux) { // verificando se o algarismo é igual ao anterior
      repeat++;
      if (repeat == 2) {
        switch (valueTest) { //verificando se o algarismo é multiplo de 5
          case romanNums[1]:
            return false;
          case romanNums[3]:
            return false;
          case romanNums[5]:
            return false;
        }
      } else if (repeat == 4) { // caso não seja multiplo de 5 o algarismo pode repetir até 3 vezes em sequencia
        return false;
      }
    } else { // não houve repetição
      repeat = 1;
    }
    aux = valueTest;
  }

  return true; // validação ok
}

function translate() {
  let value = (input.value + "").toLowerCase().split(""); // entrada da caixa de texto em letras minusculas
  let decimalValue = 0;

  if (!verification(value)) {
    result.style.opacity = 1;
    input.value = "";
    result.textContent = "Systax error";
    return;
  }

  for (let i = 0; i < value.length; i++) {
    
    //testar todos os numeros romanos para cada letra de input
    for (let j = 0; j < romanNums.length; j++) {
      //identificada a letra em romana
      if (value[i] == romanNums[j]) {
        // caso não seja a ultima letra, testar o se a próxima é maior ou menor
        if (i < value.length - 1) {
          let flag = true;
          for (let k = j + 1; k < romanNums.length; k++) {
            //testando se a próxima letra de entra é um número maior que a atual
            if (value[i + 1] == romanNums[k]) {
              decimalValue += decimalNums[k] - decimalNums[j];
              i++;
              flag = false;
              break;
            }
          }

          // se a flag for true significa que a próxima letra de entrada representa um valor menor ou igual do que a atual
          if (flag) {
            decimalValue += decimalNums[j];
          }
        } else {
          // se a letra testada for a ultima é só somar msm
          decimalValue += decimalNums[j];
        }
        break;
      }
    }
  }

  result.style.opacity = 1;
  input.value = "";
  result.textContent = decimalValue;
}
