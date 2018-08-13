// var form = document.querySelector('.')
var fields = document.querySelectorAll('.form-control');
var form = document.getElementById('registration');
var password = form.querySelector('.password');
var confirmPassword = form.querySelector('.confirmPassword');
var btn = document.getElementById('btn');
var email = document.getElementById('email');
var isErrors = false;

function myTrim (input) {
    input.value = input.value.trim();

}


var generateError = function (text) {
    isErrors = false;
    var error = document.createElement('div');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = text;
    isErrors = true;
    return error;
}

var removeValidation = function () {
    var errors = document.querySelectorAll('.error');

    for (var i = 0; i < errors.length; i++) {
        errors[i].remove();
    }

}

var checkFieldsPresence = function () {
    var bool = false
    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            console.log('field is blank', fields[i]);
            var error = generateError('Cannot be blank');
            form[i].parentElement.insertBefore(error, fields[i])
            bool = true;
        }
    }
    return bool
}

function validateEmail(email) {
    var bool = false
    var re = /\S+@\S+\.\S+/;
    if (email.value !== re.value) {
        console.log('not equals');
        console.log(email);
        var error = generateError('Invalid email');
        email.parentElement.insertBefore(error, email);
        bool = true;
    }
    return bool
}

var checkPasswordMatch = function () {
    var bool = false
    if (password.value !== confirmPassword.value) {
        console.log('not equals');
        console.log(confirmPassword);
        var error = generateError('Password doesnt match');
        password.parentElement.insertBefore(error, password);
        bool = true;
    }
    return bool

}


function beforeSubmit() {
    removeValidation();


    if (!checkFieldsPresence() && !checkPasswordMatch() && !validateEmail(email) ) {
        console.log(1);
        document.getElementById('registration').submit()
    }


}

