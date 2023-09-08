function converter(){
    document.getElementById("converter-form").addEventListener("submit", function (event) {
         event.preventDefault();

         const measurement = document.getElementById("measurement").value;
         const value = parseFloat(document.getElementById("value").value);
         let result = 0;

         switch (measurement) {
             case "teaspoons-to-tablespoons":
                 result = value / 3.0;
                 break;
             case "tablespoons-to-teaspoons":
                 result = value * 3.0;
                 break;
             case "cups-to-milliliters":
                 result = value * 240.0;
                 break;
             case "milliliters-to-cups":
                 result = value / 240.0;
                 break;
             case "gallons-to-liters":
                 result = value * 3.8;
                 break;
         }
         document.getElementById("result-value").textContent = result;
    });
}

converter();