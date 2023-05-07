const cardContainer = document.getElementById('card-container');

const cards = [
  { title: 'Card 1', content: 'This is the content of card 1.' },
  { title: 'Card 2', content: 'This is the content of card 2.' },
  { title: 'Card 3', content: 'This is the content of card 3.' }
];

cards.forEach(card => {
  const cardDiv = document.createElement('div');
  cardDiv.classList.add('card');

  const title = document.createElement('h2');
  title.textContent = card.title;

  const content = document.createElement('p');
  content.textContent = card.content;

  cardDiv.appendChild(title);
  cardDiv.appendChild(content);

  cardContainer.appendChild(cardDiv);
});
