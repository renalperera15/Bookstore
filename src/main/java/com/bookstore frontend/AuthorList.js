import React, { useEffect, useState } from 'react';
import api from '../services/api';

function AuthorList() {
  const [authors, setAuthors] = useState([]);

  useEffect(() => {
    api.get('/authors')
      .then(res => setAuthors(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Authors</h2>
      <ul>
        {authors.map(author => (
          <li key={author.id}>{author.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default AuthorList;
