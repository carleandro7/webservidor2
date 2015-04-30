/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.great.util;

/**
 * Constants used on GCM service communication.
 */
public final class Constants {

   /**
   * Chave da API do Google GCM
   */
   public static final String API_KEY = "AIzaSyCmu6aVxeFh6ZQ_u1J7DXg_fndmeXGP83g";
   
   
   public static final int JOGADOR_CADASTRAR = 1;
   public static final int JOGADOR_CADASTRAR_JOGO = 2;
   public static final int JOGADOR_SETLOCALIZACAO = 3;
   public static final int JOGADOR_LOGIN = 4;
   public static final int JOGADOR_REGDISPOSITIVO = 5;
   
   
   
   public static final int JOGO_NEWJOGO = 100;
   public static final int JOGO_LISTAJOGOS = 101;
   public static final int JOGO_MECANICAS = 102;
   public static final int JOGO_GRUPOSJOGADORES = 103;
   public static final int JOGO_GRUPOSJOGADOR = 104;
   public static final int JOGO_LISTJOGOSEXEFILHO = 105;
   public static final int JOGO_ESTADOMECANICA = 106;
   public static final int JOGO_SET_ESTADOEXE = 107;
   public static final int JOGO_SET_ESTADOFINALIZAR = 108;
   public static final int JOGO_MECDOWNLOAD = 109;
   public static final int JOGO_JOGADOR_DADOSPERSONAGEM = 110;
   
   
   
   
  private Constants() {
    throw new UnsupportedOperationException();
  }

}
