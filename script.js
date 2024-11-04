/**
 * 
 */function login(){
	document.getElementById("loginForm").addEventListener("submit", function(event) {
	    const username = document.getElementById("username").value;
	    const password = document.getElementById("password").value;
	    const errorMessage = document.getElementById("errorMessage");

	    if (!username || !password) {
	        event.preventDefault();
	        errorMessage.textContent = "Please enter both username and password.";
	    } else {
	        errorMessage.textContent = "";
	    }
	});
 }

