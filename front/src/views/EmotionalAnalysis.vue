<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
// Ant Design Vue components
import { message } from 'ant-design-vue';

const text = ref('');
const result = ref(null);
const loading = ref(false);

const analyzeText = async () => {
  if (!text.value.trim()) return message.warning('请输入文本');
  loading.value = true;
  try {
    const response = await axios.post('/api/emotional/analyze', { text: text.value }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    result.value = response.data;
  } catch (error) {
    console.error('分析失败:', error);
    message.error('分析失败，请稍后重试');
    result.value = null;
  } finally {
    loading.value = false;
  }
};
</script>

<template>
  <a-card style="max-width: 600px; margin: 2rem auto;">
    <a-typography-title level="4">情感倾向分析</a-typography-title>
    <a-form layout="vertical">
      <a-form-item label="请输入需要分析的文本（最多2048字符）">
        <a-textarea v-model:value="text" :rows="5" :maxlength="2048" :disabled="loading" allow-clear />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="analyzeText" :loading="loading" block>
          {{ loading ? '分析中...' : '开始分析' }}
        </a-button>
      </a-form-item>
    </a-form>
    <a-divider />
    <template v-if="result">
      <a-descriptions title="分析结果" bordered column="1">
        <a-descriptions-item label="情感极性">
          {{ result.sentiment === 2 ? '积极' : result.sentiment === 1 ? '中性' : '消极' }}
        </a-descriptions-item>
        <a-descriptions-item label="置信度">
          {{ (result.confidence * 100).toFixed(2) }}%
        </a-descriptions-item>
        <a-descriptions-item label="积极概率">
          {{ (result.positiveProb * 100).toFixed(2) }}%
        </a-descriptions-item>
        <a-descriptions-item label="消极概率">
          {{ (result.negativeProb * 100).toFixed(2) }}%
        </a-descriptions-item>
      </a-descriptions>
    </template>
    <a-result v-else-if="text" status="info" title="无分析结果" style="margin-top: 1rem;" />
  </a-card>
</template>

<style scoped>
/* Ant Design components handle most styling. */
</style>
