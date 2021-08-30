let account;

document.addEventListener("DOMContentLoaded", function () {
    console.log(JSON.parse(localStorage.getItem("account")));
    account = JSON.parse(localStorage.getItem("account"));

    document.getElementById("accountName").innerHTML = account.username;
    console.log(account);
    document.getElementById("accountBalance").innerHTML = account.balance;
});

function deposit() {
    sendInformation("deposit");
}

function withdraw() {
    sendInformation("withdraw");
}

function sendInformation(url) {
    clearError();

    const amount = document.getElementById("amount");
    const sum = Number(amount.value);

    fetch(`http://localhost:8080/bank/${url}/${account.username}/${sum}`)
        .then((response) => response.json())
        .then(function (data) {
            console.log(data);
            if (data.status) {
                document.getElementById("accountBalance").innerHTML = data.account.balance;
            } else {
                document.getElementById("errorMessageBank").innerHTML = data.message;
            }
        });
}

function logout() {
    localStorage.clear();
    window.location.replace("/index.html");
}

function clearError() {
    document.getElementById("errorMessageBank").innerHTML = "";
}
