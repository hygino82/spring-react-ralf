import { useState } from "react";

function Tabela() {
    const lista = [
        {
            codigo: 1,
            nome: 'Maionese',
            marca: 'Soya'
        },
        {
            codigo: 2,
            nome: 'Pizza',
            marca: 'Sadia'
        },
        {
            codigo: 3,
            nome: 'Leite',
            marca: 'Tirol'
        },
        {
            codigo: 4,
            nome: 'Mortadela',
            marca: 'Aurora'
        }
    ];

    return (
        <table className="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nome</th>
                    <th>Marca</th>
                    <th>Selecionar</th>
                </tr>
            </thead>
            <tbody>
                {lista.map(x =>
                    <tr>
                        <td>{x.codigo}</td>
                        <td>{x.nome}</td>
                        <td>{x.marca}</td>
                        <td>Ação</td>
                    </tr>
                )}
            </tbody>
        </table>
    );
}

export default Tabela;