function onDivClick() {
    alert("Div clicked!");
    const basePath = "/atm/main/withdraw";
    const url = `${basePath}/receipt/download`;
    // Example: Send a request to the server using Fetch API
    fetch(url,{
        method: 'GET'
    })
    .then(response => response.text())
    .then(result => {
        console.log("Operation result:", result);
    })
    .catch(error => console.error("Error:", error));
}