import React, { useState } from 'react';
import api from '../services/api';

function AddBook() {
  const [title, setTitle] = useState('');
  const [price, setPrice] = useState('');
  const [authorId, setAuthorId] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    api.post('/books', {
      title,
      price,
      author: { id: authorId }
    }).then(() => {
      alert('Book added successfully');
      setTitle('');
      setPrice('');
      setAuthorId('');
    }).catch(err => console.error(err));
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Book</h2>
      <input placeholder="Title" value={title} onChange={e => setTitle(e.target.value)} required />
      <input placeholder="Price" type="number" value={price} onChange={e => setPrice(e.target.value)} required />
      <input placeholder="Author ID" value={authorId} onChange={e => setAuthorId(e.target.value)} required />
      <button type="submit">Add Book</button>
    </form>
  );
}

export default AddBook;
