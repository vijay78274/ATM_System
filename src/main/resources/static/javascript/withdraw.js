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

    if (number === '') {
        alert('Please enter a number.');
        return;
    }

    try {
        const response = await fetch('/atm/main/withdraw', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken 
            },
            body: JSON.stringify({ amount: number })
        });

        const result = await response.text();
        alert(result);
        inputField.value = ''; 
    } catch (error) {
        console.error('Error sending number:', error);
        alert('Failed to send number to the server.');
    }
}