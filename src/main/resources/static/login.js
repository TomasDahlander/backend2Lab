function login() {
    fetchForLogin();
}

function fetchForLogin() {
    const name = document.getElementById("loginName");

    fetch(`http://localhost:8080/bank/login/${name.value}`)
        .then((response) => response.json())
        .then(function (data) {
            console.log(data);
            if (data.status) {
                localStorage.setItem("account", JSON.stringify(data.account));
                window.location.replace("/bank.html");
            } else {
                document.getElementById("errorMessageLogin").innerHTML = data.message;
            }
        });
}

function creatingNewAccount() {
    fetchForOpenAccount();
}

function fetchForOpenAccount() {
    const name = document.getElementById("createName");

    fetch(`http://localhost:8080/bank/openAccount/${name.value}`)
        .then((response) => response.json())
        .then(function (data) {
            console.log(data);
            if (data.status) {
                localStorage.setItem("account", JSON.stringify(data.account));
                window.location.replace("/bank.html");
            } else {
                document.getElementById("errorMessageCreate").innerHTML = data.message;
            }
        });
}
