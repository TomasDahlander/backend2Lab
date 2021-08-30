function login() {
    const name = document.getElementById("loginName");
    fetchInformation("login", name.value, "errorMessageLogin");
}

function creatingNewAccount() {
    const name = document.getElementById("createName");
    fetchInformation("openAccount", name.value, "errorMessageCreate");
}

function fetchInformation(url, name, errorMessage) {
    fetch(`http://localhost:8080/bank/${url}/${name}`)
        .then((response) => response.json())
        .then(function (data) {
            console.log(data);
            if (data.status) {
                localStorage.setItem("account", JSON.stringify(data.account));
                window.location.replace("/bank.html");
            } else {
                document.getElementById(errorMessage).innerHTML = data.message;
            }
        });
}
