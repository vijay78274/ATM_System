function onDivClick(){
    console.log("div clicked")
    const invoice = document.getElementById("receipt")
    console.log(invoice)
    const options = {
        margin: 1,
        filename: 'transaction_receipt.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'in', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().set(options).from(invoice).save()
}