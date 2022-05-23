keytool -import \
    -alias server \
    -file rootca.crt \
    -keystore cacerts \
    -storepass changeit \
    -validity 3650 \
    -noprompt