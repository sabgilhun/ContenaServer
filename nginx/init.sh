if [ "$#" -lt 1 ]; then
    echo 'Require parameter "id", "password"'
	exit 1
fi

docker run --rm --entrypoint htpasswd registry:2.6.2 -Bbn $1 $2 > auth/registry.htpasswd