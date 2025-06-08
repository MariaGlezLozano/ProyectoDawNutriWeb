<%-- 
    Document   : CalculadoraIMC
    Created on : 5 jun 2025, 17:59:01
    Author     : Maria
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .calculadora-imc {
        max-width: 400px;
        margin: 30px auto;
        padding: 25px;
        border-radius: 15px;
        box-shadow: 0 0 15px rgba(0,0,0,0.1);
        background-color: #f9f9f9;
    }

    .calculadora-imc h3 {
        text-align: center;
        margin-bottom: 20px;
    }

    .pantalla-resultado {
        background-color: #e9ecef;
        padding: 10px;
        border-radius: 8px;
        text-align: center;
        font-size: 1.4em;
        font-weight: bold;
        color: #333;
    }
</style>

<div class="calculadora-imc">
    <h3>🧮  Calculadora de IMC</h3>

    <div class="mb-3">
        <label for="peso" class="form-label">Peso (kg):</label>
        <input type="number" step="0.1" class="form-control" id="peso" placeholder="Ej. 70">
    </div>

    <div class="mb-3">
        <label for="estatura" class="form-label">Estatura (cm):</label>
        <input type="number" step="0.1" class="form-control" id="estatura" placeholder="Ej. 170">
    </div>

    <div class="mb-3">
        <label class="form-label">Resultado (IMC):</label>
        <div id="resultado" class="pantalla-resultado">—</div>
    </div>

    <button type="button" class="btn btn-success w-100" onclick="calcularIMC()">Calcular IMC</button>
</div>

