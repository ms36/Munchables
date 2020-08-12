const result = document.getElementById('res');
const buttonZero = document.getElementById('btn0');
buttonZero.addEventListener('click', () => {addText('0')});

const buttonOne = document.getElementById('btn1');
buttonOne.addEventListener('click', () => {addText('1')});

const buttonClear = document.getElementById('btnClr');
buttonClear.addEventListener('click', clear);

const buttonEqual = document.getElementById('btnEql');
buttonEqual.addEventListener('click', equal);

const buttonAdd = document.getElementById('btnAdd');
buttonAdd.addEventListener('click', () => {addText('+')});

const buttonSubtract = document.getElementById('btnSub');
buttonSubtract.addEventListener('click', () => {addText('-')});

const buttonMultiply = document.getElementById('btnMul');
buttonMultiply.addEventListener('click', () => {addText('*')});

const buttonDivide = document.getElementById('btnDiv');
buttonDivide.addEventListener('click', () => {addText('/')});

let eqaulPressed = false;

function addText(text) {        
    if(eqaulPressed) {
        clear();
        eqaulPressed = false;
    }
    result.innerHTML += text;
}

function clear() {
    result.innerHTML = "";
}

function equal() {    
    eqaulPressed = true;
    let text = result.innerHTML.split(/(\d+)([+\-*\/])(\d+)/);
    
    const operand1 = text[1];
    const operand2 = text[3];
    const operator = text[2];
    
    let operandTotal1 = 0;
    let operandTotal2 = 0;
   
    operandTotal1 = binaryToDecimal(operand1);
    operandTotal2 = binaryToDecimal(operand2);
   
    const numCalc = doMath(operandTotal1, operator, operandTotal2);    
      
    addText(decimalToBinary(numCalc));
    eqaulPressed = true;
}

function doMath(operand1, operator, operand2) {
    switch(operator) {
        case '+': return operand1 + operand2;
        case '-': return operand1 - operand2;
        case '*': return operand1 * operand2;
        case '/': return Math.floor(operand1 / operand2); 
            
    }
}
function binaryToDecimal(operand) {
    let total = 0;
    let exponent = 0;
    let number = 0;
    for(let i = operand.length - 1; i >= 0; i--) {
        number = parseInt(operand.charAt(i));
        if(number === 0 && exponent === 0) {
            exponent++;
            continue;
        }
        total += Math.pow(2 * number, exponent);         
        exponent++;
    }
    return total;
}

function decimalToBinary(decimalNumber) {
    let result = "";
    
    while(decimalNumber > 0) {
        result += decimalNumber % 2;
        decimalNumber = Math.floor(decimalNumber / 2);
    }
    const temp = result.split('.');
    
    return reverse(temp[0]);    
}

function reverse(str){
  let reversed = "";
  for(let char of str){
    reversed = char + reversed;
  }
    
  return reversed;
}