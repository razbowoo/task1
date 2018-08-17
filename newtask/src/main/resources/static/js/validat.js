const fields = document.querySelectorAll('.form-control');
const form = document.getElementById('registration');

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

function validateEmail() {
    const email = document.getElementById('email').value;
    let reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!reg.test(email)) {
        console.log (email);
        alert('Please Write Valid Email Address');
        return false;
    }
    return true;
}

function checkPasswordMatch() {
    const password = form.querySelector('.password').value;
    const confirmPassword = form.querySelector('.confirmPassword').value;
    if (password !== confirmPassword) {
        console.log(password);
        console.log(confirmPassword);
        alert("Passwords Do not match");
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








