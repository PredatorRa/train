<template>
    <div v-if="!param.date">
        请到余票查询里选择一趟列车，
        <router-link to="/ticket">
            跳转到余票查询
        </router-link>
    </div>
    <div v-else>
        <p>
            日期：{{ param.date }}，车次：{{ param.trainCode }}，出发站：{{ param.start }}，到达站：{{ param.end }}
        </p>
        <p>
            {{ list }}
        </p>
    </div>
</template>

<script>

import { defineComponent, ref, onMounted } from 'vue';
import axios from "axios";
import {notification} from "ant-design-vue";
import {useRoute} from "vue-router";


export default defineComponent({
    name: "welcome-view",
    setup() {
        const route = useRoute();
        const param = ref({});
        param.value = route.query;
        const list = ref();

        // 查询一列火车的所有销售信息
        const querySeat = () => {
            axios.get("/business/seat-sell/query", {
                params: {
                    date: param.value.date,
                    trainCode: param.value.trainCode,
                }
            }).then((response) => {
                let data = response.data;
                if (data.success) {
                    list.value = data.content;
                } else {
                    notification.error({description: data.message});
                }
            });
        };

        onMounted(() => {
            if (param.value.date) {
                querySeat();
            }
        });

        return {
            param,
            querySeat,
            list
        };
    },
});
</script>
