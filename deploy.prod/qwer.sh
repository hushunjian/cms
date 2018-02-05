BASE_DIR=$(cd -P -- "$(dirname -- "$0")"; pwd -P)
cd $BASE_DIR
    sh shutdown.sh
    sh configure.sh
    sh deploy.sh
    sh rsync.sh
    sh startup.sh
cd -

