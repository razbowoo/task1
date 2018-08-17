var fields = document.querySelectorAll('.form-control');
var form = document.getElementById('registration');

var isErrors = false;

function myTrim(input) {
    input.value = input.value.trim();
}

function generateError(text) {
    isErrors = false;
    var error = document.createElement('div');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = text;
    isErrors = true;
    return error;
}

function removeValidation() {
    var errors = document.querySelectorAll('.error');
    for (var i = 0; i < errors.length; i++) {
        errors[i].remove();
    }
}

function checkFieldsPresence() {
    var bool = false;
    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            var error = generateError('Cannot be blank');
            form[i].parentElement.insertBefore(error, fields[i]);
            bool = true;
        }
    }
    return bool;
}

function validateEmail() {
    var email = document.getElementById('email').value;
    var reg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    if (!reg.test(email)) {
        console.log (email);
        alert('Please Write Valid Email Address');
        return false;
    }   
    return true;
}

function checkPasswordMatch() {
    var password = form.querySelector('.password').value;
    var confirmPassword = form.querySelector('.confirmPassword').value;
    if (password !== confirmPassword) {
        console.log(password);
        console.log(confirmPassword);
        alert("Passwords Do not match");
        return false;
    }
    if (password.length < 8) {
        alert("Minimum weight 8");
        return false;
    }
    return true;
}


function beforeSubmit() {
    removeValidation();
    if (!checkFieldsPresence() && validateEmail()  && checkPasswordMatch()) {
        document.getElementById('registration').submit()
    }
}








