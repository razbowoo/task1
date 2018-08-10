// var form = document.querySelector('.')
var fields = document.querySelectorAll('.form-control')
var form = document.getElementById('registration')
var password = form.querySelector('.password')
var confirmPassword = form.querySelector('.confirmPassword')
var btn=document.getElementById('btn')

var generateError = function (text) {
    var error = document.createElement('div')
    error.className = 'error'
    error.style.color = 'red'
    error.innerHTML = text
    return error
}

var removeValidation = function () {
    var errors = document.querySelectorAll('.error')

    for (var i = 0; i < errors.length; i++) {
        errors[i].remove()
    }
}

var checkFieldsPresence = function () {
    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            console.log('field is blank', fields[i])
            var error = generateError('Cannot be blank')
            form[i].parentElement.insertBefore(error, fields[i])

        }
    }
}

var checkPasswordMatch = function () {
    if (password.value !== confirmPassword.value) {
        console.log('not equals')
        var error = generateError('Password doesnt match')
        password.parentElement.insertBefore(error, password)
    }
}

form.addEventListener('submit', function (event) {
    event.preventDefault()

    removeValidation()

    checkFieldsPresence()

    checkPasswordMatch()
    console.log(1)
})
