## Projeto Simulador de Batalha de Criaturas Mitícas!

Desenvolvido por: Maria Luiza Borges e Gabriel Pinheiro
Disciplina: Projeto de Simulador de Batalha de Criaturas Místicas
Data: 2025

## 🎯 Objetivos do Projeto
Criar um sistema completo de batalha por turnos entre criaturas místicas, 
onde cada criatura possui habilidades especiais, tipos elementais e características únicas. 
O projeto visa demonstrar os princípios de orientação a objetos, testes unitários e uso de mocks para comportamentos complexos.

## 🏗️ Arquitetura do Sistema
Diagrama de Classes Simplificado:
text
📦 model
 ┣━━ 🧬 Criatura (nome, hp, atk, def, spd)
 ┣━━ 🔥 TipoElemental (FOGO, ÁGUA, TERRA, AR, LUZ, TREVAS)
 ┣━━ ⚡ Habilidade (Curar, Congelar, Envenenar)
 ┣━━ 💊 EfeitoStatus (Queimadura, Congelamento, Veneno)

 
📦 service
 ┣━━ ⚔️ BatalhaService (gerencia turnos e batalhas)
 ┣━━ 🔮 GerenciadorEfeitos (controla efeitos de status)
 ┗━━ 🧮 CalculadoraElemental (cálculos de vantagem)
 
📦 test
 ┣━━ 🧪 Testes unitários com JUnit
 ┗── 🎭 Mocks com Mockito

## ⚔️ Sistema de Batalha
Turnos baseados em velocidade

Cálculo de dano considerando defesa e vantagem elemental

Aplicação de efeitos de status

Condições de vitória/derrota

## 🔮 Habilidades Especiais
Curar: Restaura pontos de vida

Congelar: Impede ações por turnos

Envenenar: Dano contínuo ao longo do tempo

## 💊 Efeitos de Status
Queimadura: Dano percentual por turno

Congelamento: Imobilização temporária

Envenenamento: Dano fixo por turno
