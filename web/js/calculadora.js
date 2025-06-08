/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function calcularIMC() {
    const peso = parseFloat(document.getElementById("peso").value);
    const estaturaCm = parseFloat(document.getElementById("estatura").value);
    const resultadoDiv = document.getElementById("resultado");

    if (!peso || !estaturaCm || peso <= 0 || estaturaCm <= 0) {
        resultadoDiv.innerText = "Datos inválidos";
        resultadoDiv.style.color = "red";
        return;
    }

    const estaturaM = estaturaCm / 100;
    const imc = peso / (estaturaM * estaturaM);
    let clasificacion = "";
    let color = "";

    if (imc < 18.5) {
        clasificacion = "Bajo peso";
        color = "#007bff";
    } else if (imc < 25) {
        clasificacion = "Peso normal";
        color = "#28a745";
    } else if (imc < 30) {
        clasificacion = "Sobrepeso";
        color = "#ffc107";
    } else {
        clasificacion = "Obesidad";
        color = "#dc3545";
    }

    resultadoDiv.innerText = `${imc.toFixed(2)} (${clasificacion})`;
    resultadoDiv.style.color = color;
}

