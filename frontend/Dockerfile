# Use uma imagem base do Node
FROM node:latest

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas os manifestos de pacote primeiro (para aproveitar cache do Docker)
COPY package*.json ./

# Instala as dependências
RUN npm install

# --- A CORREÇÃO ESTÁ AQUI ---
# Copia todo o restante do código fonte para dentro da imagem
COPY . .

# Comando padrão (já adicionando o --host para garantir acesso externo)
CMD ["npm", "run", "dev", "--", "--host"]
