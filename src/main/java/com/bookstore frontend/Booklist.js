import React, { useEffect, useState } from 'react';
import api from '../services/api';

function BookList() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    api.get('/books')
      .then(res => setBooks(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Books</h2>
      <ul>
        {books.map(book => (
          <li key={book.id}>
            <strong>{book.title}</strong> - ${book.price}
            <br />
            Author: {book.author?.name}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BookList;
