<template>
  <div class="editor">
    <h2>自由练习场</h2>
    <MdEditor :value="value" :handle-change="onChange" />
  </div>
</template>

<script setup lang="ts">
import MdEditor from "@/plugins/MdEditor.vue";
import { ref } from "vue";

const value = ref();
const onChange = (v: string) => {
  value.value = v;
};
</script>

<style scoped>
.editor {
  max-width: 1700px;
}
</style>
