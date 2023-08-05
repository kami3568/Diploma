
FROM node:latest
WORKDIR /app
COPY ["package.json", "package-lock.json*", "./"]
RUN npm install
COPY . .
EXPOSE 9999
CMD ["npm", "start"]