function appendNumber(number) {
    const input = document.getElementById('numberInput');
    input.value += number;
}

function clearInput() {
    const input = document.getElementById('numberInput');
    input.value = '';
}

function deleteLast() {
    const input = document.getElementById('numberInput');
    input.value = input.value.slice(0, -1);
}

async function sendAmount() {
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const inputField = document.getElementById('numberInput');
    const number = inputField.value;
    const toAccountInputField = document.getElementById('toAccount');
    const toAccountNumber = toAccountInputField.value;

    if (number === '') {
        alert('Please enter a number.');
        return;
    }
    if(toAccountNumber==' '){
        alert('please enter a accountNumber.');
        return;
    }

    try {
        const response = await fetch('/atm/main/transfer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken 
            },
            body: JSON.stringify({ amount: number, toAccount: toAccountNumber})
        });
    
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error('Transfer failed: ' + errorText);
        }

        const data = await response.json();
        if (data.status === 'success') {
            // Redirect to the receipt page with query parameters
            const redirectUrl = `/atm/main/transfer/receipt?amount=${data.amount}&fromAccount=${data.fromAccount}&toAccount=${data.toAccount}&transactionId=${data.transactionId}&transactionDate=${data.transactionDate}&status=${data.status}`;
            window.location.href = redirectUrl;
        } else {
            alert('Transfer failed: ' + data.message);
        }
    } catch (error) {
        console.error('Error sending number:', error);
        alert('Failed to send number to the server.');
    }
}