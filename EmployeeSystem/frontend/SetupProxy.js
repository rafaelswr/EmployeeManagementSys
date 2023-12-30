import { createProxyMiddleware } from 'http-proxy-middleware';

export default function(app) {
  app.use(
    '/sse-employees',
    createProxyMiddleware({
      target: 'https://0cff-2001-8a0-f76b-1400-6d14-7794-2187-3aee.ngrok-free.app',
      changeOrigin: true,
    })
  );
};