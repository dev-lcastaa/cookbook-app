// Add event listeners to the buttons
document.addEventListener("DOMContentLoaded", function () {
    var loginButton = document.getElementById("loginButton");
    var signupButton = document.getElementById("signupButton");

    if (loginButton) {
        loginButton.addEventListener("click", function () {
            loginUser();
        });
    }
    if (signupButton) {
        signupButton.addEventListener("click", function () {
            signupUser();
        });
    }

});

function loginUser() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

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
        sessionStorage.setItem("user",user);
        window.location.href = "/v1/account";
    })
    .catch(function (error) {
        // Handle errors
        console.error("Login failed:", error);
    });
}

function signupUser() {
    var signupUsername = document.getElementById("signupUsername").value;
    var signupEmail = document.getElementById("signupEmail").value;
    var signupPassword = document.getElementById("signupPassword").value;

    var data = {
        username: signupUsername,
        email: signupEmail,
        password: signupPassword,
        role : "USER"
    };

    fetch("/api/v1/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Signup failed.");
        }
    })
    .then(function (data) {
        // Handle successful signup
        console.log("Signup successful:", data);
    })
    .catch(function (error) {
        // Handle errors
        console.error("Signup failed:", error);
    });
}
