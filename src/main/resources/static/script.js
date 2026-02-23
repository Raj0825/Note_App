document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const messageEl = document.getElementById('message');

    // 1. Encode the email and password into a Base64 string for Basic Auth
    const credentials = btoa(`${email}:${password}`);
    const authHeader = `Basic ${credentials}`;

    try {
        // 2. Test credentials by fetching the notes
        const response = await fetch('/notes', {
            method: 'GET',
            headers: {
                'Authorization': authHeader,
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            messageEl.textContent = 'Login successful!';
            messageEl.style.color = '#4caf50';

            // 3. Save the token to localStorage
            localStorage.setItem('authToken', authHeader);
            localStorage.setItem('userEmail', email);

            // Redirect to dashboard
            setTimeout(() => {
                window.location.href = '/dashboard.html';
            }, 1000);
        } else {
            messageEl.textContent = 'Invalid email or password';
            messageEl.style.color = '#d32f2f';
        }
    } catch (error) {
        messageEl.textContent = 'An error occurred. Please try again.';
        console.error('Error:', error);
    }
});