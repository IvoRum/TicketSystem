const form=document.querySelector('form')
const data = new FormData(form);
const now = new Date();
const hours = now.getHours().toString().padStart(2, '0');
const minutes = now.getMinutes().toString().padStart(2, '0');
const seconds = now.getSeconds().toString().padStart(2, '0');

const time = `${hours}:${minutes}:${seconds}`;
console.log(time);


form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = new FormData(form);
    const response =
        await fetch('http://localhost:8080/api/v1/draft/1', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: data.get('ticketId'),
                issueTime: time,
            }),
        });

});
