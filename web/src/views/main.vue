<template>
  <a-layout id="components-layout-demo-top-side-2">
    <the-header-view></the-header-view>
    <a-layout>
      <the-sider-view></the-sider-view>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import {defineComponent, ref} from 'vue';
import TheHeaderView from "@/components/the-header.vue";
import TheSiderView from "@/components/the-sider.vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({
    components: {
        TheSiderView,
        TheHeaderView,
    },
    setup() {
        const count = ref(0);
        axios.get("/member/member/count").then(response=>{
          let data = response.data;
          if(data.success){
            count.value = data.content;
          }else{
            notification.error({description:data.message});
          }
        });

        return {
          count,
        };
    },
});
</script>
<style>

</style>