const fields = document.querySelectorAll('.form-control');
const form = document.getElementById('signIn');
const password = form.querySelector('.password').value;
const email = document.getElementById('email').value;
let isErrors = false;

function myTrim(input) {
    input.value = input.value.trim();
}

function generateError(text) {
    isErrors = false;
    let error = document.createElement('div');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = text;
    isErrors = true;
    return error;
}

function removeValidation() {
    let errors = document.querySelectorAll('.error');
    for (let i = 0; i < errors.length; i++) {
        errors[i].remove();
    }
}

function checkFieldsPresence() {
    let bool = false;
    for (let i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            let error = generateError('Cannot be blank');
            form[i].parentElement.insertBefore(error, fields[i]);
            bool = true;
        }
    }
    return bool;
}

function validate(email) {
    let reg = /\w+@\w+\.\w/i;
    if (!reg.test(email)) {
        alert('Invalid Email Address');
        return false;
    }
    return true;
}


function beforeSubmit() {
    removeValidation();
    if (!checkFieldsPresence() && validate(email)) {
        document.getElementById('login').submit()
    }
}