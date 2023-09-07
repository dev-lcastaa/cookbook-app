// adds event listeners for page
function addEventListeners() {
    const loginButton = document.getElementById("loginButton");
    const registerButton = document.getElementById("registerButton");

    if (registerButton) {
        registerButton.addEventListener("click", register);
    }
    if (loginButton) {
        loginButton.addEventListener("click", login);
    }
}

//function to login when the button is clicked
function login() {
    var username = document.getElementById("usernameLogin").value;
    var password = document.getElementById("passwordLogin").value;

    console.log(username);
    console.log(password);
    var data = {
        username: username,
        password: password
    };

    fetch("/api/v1/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (response.ok) {
            // Parse the response body to get the token
            return response.json();
        } else {
            throw new Error("Login failed.");
        }
    })
    .then(function (data) {
        // Handle successful login
        console.log("Login successful:", data);
        var token = data.token;
        var user = JSON.stringify(data.user);
        sessionStorage.setItem("token", token);
        sessionStorage.setItem("user", user);
        window.location.href = "/v1/main";
    })
    .catch(function (error) {
        // Handle errors, e.g., display an error message to the user
        console.error("Login failed:", error);
    });
}

//function to register when the register button is clicked
function register() {
    console.log("Register button clicked. Implement registration logic here.");
}

// Add event listeners when the DOM content is loaded
document.addEventListener("DOMContentLoaded", addEventListeners);