import React, { useEffect, useState } from 'react';
import api from '../services/api';

function CustomerList() {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    api.get('/customers')
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Customers</h2>
      <ul>
        {customers.map(customer => (
          <li key={customer.id}>{customer.name} - {customer.email}</li>
        ))}
      </ul>
    </div>
  );
}

export default CustomerList;
