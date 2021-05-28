FROM clojure
COPY . /usr/src/anonydog
WORKDIR /usr/src/anonydog
CMD ["lein", "run"]
