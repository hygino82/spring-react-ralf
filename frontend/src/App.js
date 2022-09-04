import Formulario from './Formulario';
import Tabela from './Tabela';
import './App.css';
import { useEffect, useState } from 'react';

function App() {

  const [btnCadastrar, setBtnCadastar] = useState(true);
  const [produtos, setProdutos] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/produto/listar')
      .then(retorno => retorno.json())
      .then(retorno_convertido => setProdutos(retorno_convertido));
  }, []);

  return (
    <div>

      <Formulario botao={btnCadastrar} />
      <Tabela vetor={produtos} />
    </div>
  );
}

export default App;
