const form = document.getElementById('formVeiculo');

async function listarVeiculos() {
    const resposta = await fetch('http://localhost:8080/veiculos');
    const listaDeVeiculos = await resposta.json();

    const corpoTabela = document.getElementById('tabelaCorpo');

    corpoTabela.innerHTML = "";

    listaDeVeiculos.forEach(veiculo => {
        corpoTabela.innerHTML += `
            <tr>
                <td>${veiculo.modelo}</td>
                <td>${veiculo.marca}</td>
                <td>${veiculo.placa}</td>
                <td>${veiculo.ano}</td>
                <td>${veiculo.cor}</td>
                <td>
                    <button class="btn-excluir" onclick="deletar(${veiculo.id})">Excluir</button>
                </td>
            </tr>
        `;
    });
}

form.addEventListener('submit', function(event) {

    event.preventDefault();
    const dadosVeiculo = {
        modelo: document.getElementById('modelo').value,
        marca: document.getElementById('marca').value,
        placa: document.getElementById('placa').value,
        ano: document.getElementById('ano').value,
        cor: document.getElementById('cor').value,
    };

    fetch('http://localhost:8080/veiculos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dadosVeiculo)
    })
        .then(resposta => {
            if (resposta.ok) {
                alert("Veículo salvo com sucesso no MySQL!");
                form.reset();
                listarVeiculos();
            } else {
                alert("Erro ao salvar o veículo.");
            }
        })
        .catch(erro => console.error("Erro na conexão:", erro));
});

async function deletar(id) {

    if (confirm("Tem certeza que deseja excluir este veículo?")) {

        const resposta = await fetch(`http://localhost:8080/veiculos/${id}`, {
            method: 'DELETE'
        });

        if (resposta.ok) {
            alert("Veículo removido com sucesso!");

            listarVeiculos();
        } else {
            alert("Erro ao tentar excluir o veículo.");
        }
    }
}

listarVeiculos();
