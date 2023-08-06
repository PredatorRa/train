<template>
  <div v-if="!param.date">
    请到余票查询里选择一趟列车，
    <router-link to="/ticket">
      跳转到余票查询
    </router-link>
  </div>
  <div v-else>
    <p>
      日期：{{param.date}}，车次：{{param.trainCode}}，出发站：{{param.start}}，到达站：{{param.end}}
    </p>
  </div>
</template>

<script>

import { defineComponent, ref } from 'vue';
import {useRoute} from "vue-router";

export default defineComponent({
  name: "welcome-view",
  setup() {
    const route = useRoute();
    const param = ref({});
    param.value = route.query;

    return {
      param
    };
  },
});
</script>
