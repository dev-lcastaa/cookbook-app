// adds event listeners for page
function addEventListeners() {
    const getstartedButton = document.getElementById("getstarted");

    if (getstartedButton) {
        getstartedButton.addEventListener("click", getstartedButtonTest);
    }

}


//function for when the button in the landing page is clicked
function getstartedButtonTest(){
  console.log("getstartedButton pressed!")
  window.location.href = "../authenticate/login.html";
}


// Add event listeners when the DOM content is loaded
document.addEventListener("DOMContentLoaded", addEventListeners);
