// Call the dataTables jQuery plugin


async function registerUser() {
    const dataUser = {}
    dataUser.nombre = document.getElementById("nombre").value;
    dataUser.apellido = document.getElementById("apellido").value;
    dataUser.email = document.getElementById("email").value;
    dataUser.password = document.getElementById("password").value;
    dataUser.repeatPassword = document.getElementById("repeatPassword").value;
    dataUser.nickname = "EzequielAibar";
    const request = await fetch('validar-formulario', {
        headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json'
        }, method: 'POST', body: JSON.stringify(dataUser)
    });

    if (request.status === 201) {
        window.location = "login.html";
    }
    const response = await request.json();
    if (response['Error'] === "P-500") {
        alert(response['Mensaje']);
    }
    console.log(response);
}

