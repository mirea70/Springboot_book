#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc
                                                            # 앞에서 넘겨준 문장을 service-url.inc에 덮어쓴다.
  echo "> 엔진엑스 Reload"
  sudo service nginx reload  # 엔진엑스 설정을 다시 불러온다( != restart  restart와 달리 끊김 없이 불러옴)
                             # 중요한 설정들은 restart를 사용해야 불러올 수 있음
}